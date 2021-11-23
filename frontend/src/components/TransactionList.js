import React, {useState} from 'react';
import { Button, List, Modal } from 'antd';
import TransferModalContent from './TransactionModalContent';

const data = [
    'Racing car sprays burning fuel into crowd.',
    'Japanese princess to wed commoner.',
    'Australian walks 100km after outback crash.',
    'Man charged over missing wedding girl.',
    'Los Angeles battles huge wildfires.',
  ];

const TransactionList = () => {
    const [modalOpen, setModalOpen] = useState(false);
    const [itemSelected, setItemSelected] = useState(null);

    const handleCancel = () => {
        setModalOpen(false);
    };

    const handleOk = () => {
        setModalOpen(false);
    }

    const handleOnClick = (item) => {
        setModalOpen(true);
        setItemSelected(item);
    }

    return (
        <>
            <List 
                dataSource={data}
                renderItem={(item) => (
                    <List.Item
                        actions={[<Button type="primary" onClick={() => {handleOnClick(item)}}>transfer</Button>]}
                    >
                        <List.Item.Meta
                            description={item}/>
                    </List.Item>
                )}
            />
            <Modal 
                visible={modalOpen}
                title={itemSelected}
                onOk={handleOk}
                onCancel={handleCancel}
                footer={[
                    <Button key="back" onClick={handleCancel}>
                        cancel
                    </Button>,
                    <Button key="submit" type="primary" onClick={handleOk}>
                        confirm transfer
                    </Button>
                ]}
                >
                <TransferModalContent item={itemSelected}/>
            </Modal>
        </>
    );
}
export default TransactionList;