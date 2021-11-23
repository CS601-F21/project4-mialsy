import { Card, Avatar, Input, Typography, Tooltip } from 'antd';
import { UserOutlined, EditOutlined, SaveOutlined, CloseSquareOutlined } from '@ant-design/icons';
import React, {useState} from 'react';

const { Paragraph } = Typography;

const Profile = () => {
    const [githubUsername] = useState("default user");
    const [name, setName] = useState('name');
    const [editable, setEditable] = useState(true);
    const toggleEditable = () => {
        setEditable(!editable);
    }

    const handleSave = () => {
        toggleEditable();
        console.log(name);
    }

    const handleCancel = () => {
        toggleEditable();
        console.log("save");
    }

    const editActions = [
        <Tooltip title="Edit">
            <EditOutlined key="edit" onClick={toggleEditable}/>
        </Tooltip>];

    const saveAndCancelActions = [
        <Tooltip title="Save">
            <SaveOutlined key="save" onClick={handleSave} />
        </Tooltip>, 
        <Tooltip title="Cancel">
            <CloseSquareOutlined key="cancel" onClick={handleCancel}/> 
        </Tooltip>];

    return (
        <Card title="Profile" 
            actions={editable ? editActions : saveAndCancelActions}
            style={{ width: 600 }}>

            <Avatar size="large" icon={<UserOutlined />} />
            <div style={{margin: 10}}>
                <div>
                    Name: 
                </div>  
                <div>
                    {editable ? 
                    <Paragraph style={{margin: 3}}>{name}</Paragraph> 
                    : 
                    <Input placeholder={name} allowClear={true} onChange={(e) => {setName(e.target.value)}}/>} 
                </div>
                <div>
                    Github username: 
                </div>
                <div>
                    <Paragraph style={{margin: 3}}>{githubUsername}</Paragraph>
                </div>
            </div>
        </Card>
    );
}
export default Profile;