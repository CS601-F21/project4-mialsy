import { setAuth } from "../utils/AuthUtil"
import { withCookies, Cookies } from 'react-cookie';

const Redirect = (props) => {

    const {cookies} = props;
    console.log(cookies);
    return <p>stupid gumball</p>

}

export default withCookies(Redirect)