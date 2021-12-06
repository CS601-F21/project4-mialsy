import { Card, Avatar, Input, Typography, Tooltip, message } from 'antd';
import { UserOutlined, EditOutlined, SaveOutlined, CloseSquareOutlined } from '@ant-design/icons';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { getAxiosOptions } from '../utils/AxiosUtil';

const { Paragraph } = Typography;

const Profile = () => {
    const [email, setEmail] = useState("");
    const [username, setName] = useState("");
    const [avatar, setAvatar] = useState("");

    const [editable, setEditable] = useState(true);

    // get user info
    const fetchData = () => {
        const opt = getAxiosOptions("get", "/profile");

        axios(opt)
            .then(res => {
                if (res.status === 200) {
                    setEmail(res.data.email);
                    setName(res.data.name);
                    setAvatar(res.data.picture);
                }
            })
            .catch(err => {
                console.log(err);
            });
    };

    const toggleEditable = () => {
        setEditable(!editable);
    }

    const handleSave = () => {
        axios.put(`/profile?name=${username}`
            ,
            {}, 
            {
                withCredentials: true
            })
            .then((res) => {
                message.success("Change name success!");
            }).catch((err) => {
                console.log(err);
            }).finally(() => {
                toggleEditable();
            });
    }

    const handleCancel = () => {
        toggleEditable();
    }

    useEffect(() => {
        fetchData();
      }, []);

    const editActions = [
        <Tooltip title="Edit">
            <EditOutlined key="edit" onClick={toggleEditable} />
        </Tooltip>];

    const saveAndCancelActions = [
        <Tooltip title="Save">
            <SaveOutlined key="save" onClick={handleSave} />
        </Tooltip>,
        <Tooltip title="Cancel">
            <CloseSquareOutlined key="cancel" onClick={handleCancel} />
        </Tooltip>];


    return (
        <Card title="Profile"
            actions={editable ? editActions : saveAndCancelActions}
            style={{ width: 600 }}>

            <Avatar size={65} src={avatar} icon={<UserOutlined />}/>
            <div style={{ margin: 10 }}>
                <h4>
                    Name:
                </h4>
                <div>
                    {editable ?
                        <Paragraph style={{ margin: 3 }}>{username}</Paragraph>
                        :
                        <Input placeholder={"Input name"}
                            allowClear={true}
                            type="text"
                            onChange={(e) => { setName(e.target.value) }}
                        />}
                </div>
                <h4>
                    Email:
                </h4>
                <div>
                    <Paragraph style={{ margin: 3 }}>{email}</Paragraph>
                </div>
            </div>
        </Card>
    );
}
export default Profile;