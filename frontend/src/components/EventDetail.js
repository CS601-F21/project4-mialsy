import React, {useEffect, useState} from 'react';
import { useParams } from 'react-router';
import { BASE_URL } from '../constants/Constant';
import axios from 'axios';
import { Descriptions, Spin } from 'antd';
import EventBuyTicketButton from './EventBuyTicketButton';

const getEventDescription = (e, setReload) => {
    return (
        <div>
            <Descriptions title={e.name} column={1}> 
                <Descriptions.Item label="Description">{e.description}</Descriptions.Item>
                {e.count !== 0 ? 
                    <Descriptions.Item label="Count">{e.count}</Descriptions.Item> : 
                    <Descriptions.Item>Sold Out</Descriptions.Item>}   
            </Descriptions>
            <EventBuyTicketButton count={e.count} id={e.id} setReload={setReload}/>
        </div>
    );
};

const EventDetail = () => {
    const params = useParams();
    const [event, setEvent] = useState(Object);
    const [reload, setReload] = useState(false);
    
    const fetchData = () =>{
        console.log("get detail")
        const opt = {
            method: "get",
            withCredentials: true,
            url: `${BASE_URL}/event?id=${params.id}`
        }
    
        axios(opt)
        .then(res => {
            if (res.status === 200) {
                const eventData = res.data;
                setEvent(eventData);
                setReload(false);
            }
        })
        .catch(err => {
            console.log(err);
        });
    }

    useEffect(() => {fetchData()}, [reload]);
    
    useEffect(() => {}, [event.id]);

    return (
        !event.id ? <div><Spin size="large" style={{width: '80%', margin: '0 auto'}} /></div> : getEventDescription(event, setReload)
    );
}
export default EventDetail;