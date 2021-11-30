import react, {useEffect, useState} from 'react';
import { useParams } from 'react-router';
import { BASE_URL } from '../constants/Constant';
import axios from 'axios';
import { Button, Descriptions, Spin } from 'antd';

const getEventDescription = (e) => {
    const handleOnClick = (id) => {
        console.log(id);
    };

    return (
        <div>
            <Descriptions title={"Event Detail"} column={1}> 
                <Descriptions.Item label="Title">{e.name}</Descriptions.Item>
                <Descriptions.Item label="Description">{e.description}</Descriptions.Item>
                {e.count !== 0 ? 
                    <Descriptions.Item label="Count">{e.count}</Descriptions.Item> : 
                    <Descriptions.Item>Sold Out</Descriptions.Item>}   
            </Descriptions>
            <Button type={'primary'} disabled = {e.count === 0} onClick={() => {handleOnClick(e.id) }}>Buy ticket</Button>
        </div>
    );
};

const EventDetail = () => {
    const params = useParams();

    const [event, setEvent] = useState(Object);

    const opt = {
        method: "get",
        url: `${BASE_URL}/event?id=${params.id}`
    }

    axios(opt)
    .then(res => {
        if (res.status === 200) {
            const eventData = res.data;
            setEvent(eventData);
        }
    })
    .catch(err => {
        console.log(err);
    });
    
    useEffect(() => {}, [event.id]);

    return (
        !event.id ? <div><Spin size="large" style={{width: '80%', margin: '0 auto'}} /></div> : getEventDescription(event)
    );
}
export default EventDetail;