import { Button } from 'antd';
import * as React from 'react';
import { Link } from 'react-router-dom';

const Login = () => {

    const onSubmit = () => {
        
    }

    return (<Button type="primary" href="http://localhost:8080/oauth2/authorization/google">Login with Github</Button>)

}
export default Login;