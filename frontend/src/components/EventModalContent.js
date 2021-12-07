import React, { useState } from 'react';
import { Form, Button, Input, InputNumber, DatePicker, Upload, message } from 'antd';
import { UploadOutlined } from '@ant-design/icons';
import axios from 'axios';
import { BASE_URL } from '../constants/Constant';

const layout = {
    labelCol: {
        span: 6,
    },
    wrapperCol: {
        span: 21,
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
    let fileCount = 0;

    const onFinish = (values) => {
        const eventInfo = values.event;
        console.log(eventInfo);
        const body = {
            name: eventInfo.name,
            description: eventInfo.description,
            count: eventInfo.count,
            time: eventInfo.time.unix(),
            location: eventInfo.location,
            picture: eventInfo.picture.file.response && eventInfo.picture.file.response.secure_url
        };
        
        console.log(body)

        axios.post(`${BASE_URL}/events`,
            body,
            {
                withCredentials: true
            })
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

    const uploadProps = {
        name: 'file',
        accept: 'image/*',
        action: `https://api.cloudinary.com/v1_1/${process.env.REACT_APP_CLOUD_NAME}/image/upload`,
        data: {'upload_preset': process.env.REACT_APP_CLOUD_PRESET},
        maxCount: 1,
        onChange(info) {
            console.log(info.file);

            if (info.file.status !== 'uploading') {
                console.log(info.file, info.fileList);
            }
            if (info.file.status === 'done') {
                console.log(`${info.file.name} file uploaded successfully`);
            } else if (info.file.status === 'error') {
                message.error(`${info.file.name} file upload failed.`);
            }
        },
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

            <Form.Item
                name={['event', 'time']}
                label="Time"
                rules={
                    [{
                        required: true,
                        type: "object"
                    }]
                }>
                <DatePicker showTime format="MM/DD/YYYY HH:mm:ss" />
            </Form.Item>


            <Form.Item
                name={['event', 'location']}
                label="Location"
                rules={[
                    {
                        required: true,
                    },
                ]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                name={['event', 'picture']}
                label="Image"
            >
                <Upload {...uploadProps}>
                    <Button icon={<UploadOutlined />}>Upload</Button>
                </Upload>
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