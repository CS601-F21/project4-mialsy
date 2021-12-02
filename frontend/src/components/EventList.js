import { BackTop, Button, Divider, List, Modal } from 'antd';
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
            withCredentials: true,
            url: `${BASE_URL}/events`
        }

        console.log(opt)
    
        axios(opt)
            .then(res => {
                if (res.status === 200) {
                    setEvents(res.data);
                    setLoading(false);
                    setReload(false);
                }
            })
            .catch(err => {
                console.log("failed!")
                console.log(err);
            });
    };
    
    useEffect(() => {fetchData()}, [reload]);

    return (
        <>
            <div style={{fontSize: 18, fontWeight: 'bold', paddingBottom: 15}}>
                Want to create your own event?
                <Button 
                    type={'primary'} 
                    shape={'round'}
                    icon={<PlusOutlined />}
                    onClick={() => setModalOpen(true) } 
                    style={{marginLeft: 10, marginTop: 20, marginBottom: 20}}
                >
                    Add a new event
                </Button>
            </div>
            <Divider> Events </Divider>
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
                    <UpCircleTwoTone twoToneColor="#3E6294" style={{fontSize: 50}}/>
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