import { BackTop, Button, List, Modal } from 'antd';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { BASE_URL } from '../constants/Constant';
import EventModalContent from './EventModalContent';
import {UpCircleTwoTone, PlusOutlined} from '@ant-design/icons';
import EventBuyTicketButton from './EventBuyTicketButton';

const EventList = () => {
    const [events, setEvents] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [loading, setLoading] = useState(true);
    const [reload, setReload] = useState(false);


    const handleCancel = () => {
        setModalOpen(false);
    };

    const fetchData = () => {
        setLoading(true);
        console.log("fetch");
        const opt = {
            method: "get",
            url: `${BASE_URL}/events`
        }
    
        axios(opt)
            .then(res => {
                if (res.status === 200) {
                    setEvents(res.data);
                    setLoading(false);
                    setReload(false);
                }
            })
            .catch(err => {
                console.log(err);
            });
    };
    
    useEffect(() => {fetchData()}, [reload]);

    return (
        <>
            <Button 
                type={'primary'} 
                shape={'round'}
                icon={<PlusOutlined />}
                onClick={() => setModalOpen(true) } 
                style={{marginTop: 20, marginBottom: 20}}
            >
                Create new event
            </Button>
            <div>
                <List
                    dataSource={events}
                    loading={loading}
                    renderItem={(item) => (
                        <List.Item
                            actions={[<Link to={`/event/${item.id}`}>view detail</Link>,
                                    <EventBuyTicketButton count={item.count} id={item.id} setReload={setReload}/>]}
                        >
                            <List.Item.Meta
                                title={<p>{item.name}</p>}
                                description={item.description} />
                        </List.Item>
                    )}
                />
                <BackTop>
                    <UpCircleTwoTone style={{fontSize: 50}}/>
                </BackTop>
            </div>
            <Modal 
                visible={modalOpen}
                title={"Create a new event"}
                onCancel={handleCancel}
                footer={null}
                >
                <EventModalContent setModalOpen={setModalOpen} setReload={setReload}/>
            </Modal>

        </>
    );
}
export default EventList;