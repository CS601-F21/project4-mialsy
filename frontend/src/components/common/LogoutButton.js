
import { Button } from "antd";
import axios from "axios";
import React from "react";
import { useCookies } from "react-cookie";
import { getAxiosOptions } from "../../utils/AxiosUtil";
import { LogoutOutlined } from "@ant-design/icons";

/**
 * On Logout Button clicked
 * 
 * @param {*} setAuth set auth state
 * @param {*} removeCookie remove cookie
 */

const onLogout = (setAuth, removeCookie) => {
  const opt = getAxiosOptions("post", "/logout");

  axios(opt)
    .then((res) => {
      setAuth(false);
      removeCookie("JSESSIONID");
      removeCookie("XSRF-TOKEN");
    })
    .catch((err) => console.log(err));
};

/**
 * Logout Button Component
 * 
 * @param {*} props props from parent component
 * @returns logout button
 */
const LogoutButton = (props) => {
  const [, , removeCookie] = useCookies("name");

  return (
    <div>
      {props.bpReached ? (
        <Button
          type="primary"
          style={{ marginBottom: 0, width: 150 }}
          onClick={() => onLogout(props.setAuth, removeCookie)}
        >
          Logout
        </Button>
      ) : (
        <LogoutOutlined onClick={() => onLogout(props.setAuth, removeCookie)} />
      )}
    </div>
  );
};

export default LogoutButton;
