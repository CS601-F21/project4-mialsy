import { Alert, Divider, Input, Typography, Comment, Avatar } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { getAxiosOptions } from "../../utils/AxiosUtil";
import { UserOutlined } from "@ant-design/icons";

const { Text } = Typography;
const { Search } = Input;

/**
 * Display user detail infomation
 * 
 * @param {*} user the user to transfer to
 * @returns description component of the user
 */
const userInfo = (user) => (
  <div style={{ marginTop: 10 }}>
    <Divider />
    <h4> Confirm user to transfer to </h4>
    <Comment
      author={user.name}
      content={user.email}
      avatar={<Avatar src={user.picture} icon={<UserOutlined />} />}
    />
  </div>
);

/**
 * Modal content for trasfering a ticket
 * Include search user and confirm transfer
 * 
 * @param {*} props props from parent
 * @returns modal content 
 */
const TransferModalContent = (props) => {
  const [toUser, setToUser] = useState(props.user);
  const [error, setError] = useState(undefined);

  const onSearch = (value) => {
    setError(undefined);

    const opt = getAxiosOptions("get", `/user?email=${value}`);

    axios(opt)
      .then((res) => {
        if (res.status === 200) {
          props.setToUser(res.data);
          setToUser(res.data);
        }
      })
      .catch((err) => {
        setError("Cannot find the user");
      });
  };

  useEffect(() => {}, [props.modalOpen]);

  return (
    <div>
      Transfer ticket of the event -{" "}
      <Text strong> {props.item.eventName} </Text>
      <div style={{ display: "flex", flex: "auto", marginTop: 20 }}>
        <Text style={{ marginTop: 10, paddingRight: 15 }}> To user: </Text>
        <Search
          placeholder="Input email"
          allowClear
          size="large"
          onSearch={onSearch}
          style={{ width: "auto" }}
          enterButton
        />
      </div>
      {toUser && userInfo(toUser)}
      {error && (
        <Alert
          message={error}
          type="error"
          showIcon
          style={{ marginTop: 20 }}
        />
      )}
    </div>
  );
};
export default TransferModalContent;
