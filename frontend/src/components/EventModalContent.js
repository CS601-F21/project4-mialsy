import React from 'react'; 
import { Form, Button, Input, InputNumber } from 'antd';
import axios from 'axios';
import { BASE_URL } from '../constants/Constant';

const layout = {
    labelCol: {
      span: 5,
    },
    wrapperCol: {
      span: 10,
    },
};

const validateMessages = {
    // eslint-disable-next-line no-template-curly-in-string
    required: '${label} is required!',
    types: {
      // eslint-disable-next-line no-template-curly-in-string
      number: '${label} is not a valid number!',
    },
    number: {
      // eslint-disable-next-line no-template-curly-in-string
      range: '${label} must be at least ${min}',
    },
};

const EventModalContent = (props) => {
    const [form] = Form.useForm();
    
    const onFinish = (values) => {
        const body = values.event;

        const opt = {
            method: "post",
            url: `${BASE_URL}/events`,
            data: body
        }

        axios(opt)
          .then(function (response) {
              form.resetFields();
              props.setModalOpen(false);
              props.setReload(true);
              console.log(response);
          })
          .catch(function (error) {
              console.log(error);
          });
    };

    return (
        <Form {...layout} 
            form={form}
            name="eventForm" 
            onFinish={onFinish} 
            validateMessages={validateMessages}
        >
            <Form.Item
            name={['event', 'name']}
            label="Title"
            rules={[
                {
                required: true,
                },
            ]}
            >
            <Input />
            </Form.Item>
            
            <Form.Item name={['event', 'description']} label="Description">
            <Input.TextArea />
            </Form.Item>
    
            <Form.Item
            name={['event', 'count']}
            label="Count"
            rules={[
                {
                required: true,
                type: 'number',
                min: 1
                },
            ]}
            >
            <InputNumber />
            </Form.Item>
    
            <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 8 }}>
            <Button type="primary" htmlType="submit">
                Create
            </Button>
            </Form.Item>
        </Form>  
    );
}
export default EventModalContent;