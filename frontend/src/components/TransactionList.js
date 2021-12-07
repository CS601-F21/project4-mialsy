import React, {useEffect, useState} from 'react';
import { BackTop, Button, List, message, Modal, Popover } from 'antd';
import TransferModalContent from './TransactionModalContent';
import { getAxiosOptions } from '../utils/AxiosUtil';
import {UpCircleTwoTone} from '@ant-design/icons';
import axios from 'axios';
import { getFormattedTime, isPassedTime } from '../utils/DisplayUtil';

const getListItem = (item) => {
    
    const content = <div>
        <h4> Time: </h4> {getFormattedTime(item.eventTime)}
        <h4 style={{paddingTop: 5}}> Location: </h4> {item.eventLocation}

        {item.eventDescription 
            && 
        <div style={{marginTop: 10}}><h4>Description:</h4> {item.eventDescription}</div>}
        
    </div>;
    return (
    <Popover placement="bottom" content={content} trigger="hover">
        <h4>{item.eventName}</h4>
    </Popover>);
}

const TransactionList = () => {
    const [transactionData, setData] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [itemSelected, setItemSelected] = useState(null);
    const [toUser, setToUser] = useState(undefined);
    const [loading, setLoading] = useState(true);
    const [reload, setReload] = useState(false);

    const fetchData = () => {
        const opt = getAxiosOptions("get", "/transactions");
        setLoading(true);

        axios(opt)
        .then(res => {
            if (res.status === 200) {
                setLoading(false);
                setReload(false);
                setData(res.data);
            }
        })
        .catch(err => {
            console.log(err);
        });
    }

    const reset = () => {
        setModalOpen(false);
        setToUser(undefined);
    };

    const handleCancel = () => {
        reset();
    };

    const handleOk = () => {
        console.log(toUser);
        console.log(itemSelected);

        axios.put(`/transaction?transactionId=${itemSelected.id}&toUser=${toUser.id}`,
            {}, 
            {
                withCredentials: true
            })
            .then((res) => {
                message.success("Transfer success!");
                setReload(true);
                reset();
            }).catch((err) => {
                console.log("failed!")
                console.log(err);
            });
    }

    const handleOnClick = (item) => {
        setModalOpen(true);
        setItemSelected(item);
    }

    useEffect(() => {
        fetchData();
    }, [reload]);

    return (
        <>
            <List 
                dataSource={transactionData}
                loading={loading}
                renderItem={(item) => (
                    <List.Item
                        actions={[
                        <Button type="primary" disabled={isPassedTime(item.eventTime)} onClick={() => {handleOnClick(item)}}>
                            {isPassedTime(item.eventTime) ? 'Past Event' : 'Transfer'}
                        </Button>]}
                    >
                        {getListItem(item)}
                    </List.Item>
                )}
            />
            <BackTop>
                    <UpCircleTwoTone twoToneColor="#3E6294" style={{fontSize: 50}}/>
                </BackTop>
            <Modal 
                destroyOnClose={true}
                visible={modalOpen}
                title={"Who do you want to transfer to?"}
                onOk={handleOk}
                onCancel={handleCancel}
                footer={[
                    <Button key="back" onClick={handleCancel}>
                        Cancel
                    </Button>,
                    <Button key="submit" type="primary" onClick={handleOk} disabled={!toUser}>
                        Confirm transfer
                    </Button>
                ]}
                >
                <TransferModalContent 
                    item={itemSelected} 
                    user={toUser} 
                    setToUser={setToUser} 
                    modalOpen={modalOpen}
                />
            </Modal>
        </>
    );
}
export default TransactionList;