import { List } from 'antd';
import react, {useEffect, useState} from 'react'; 
import { Link } from 'react-router-dom';
import axios from 'axios';
import { BASE_URL } from '../constants/Constant';

const EventList = () => {
    const [events, setEvents] = useState([]);
    const opt = {
        method: "get",
        url: `${BASE_URL}/events`
    }

    useEffect(() => {
            axios(opt)
            .then(res => {
                if (res.status === 200) {
                    setEvents(res.data);
                }
            })
            .catch(err => {
                console.log(err);
            });
        }
    , []);

    return (
        <List 
            dataSource={events}
            loading={events.length === 0}
            renderItem={(item) => (
                <List.Item
                    actions={[<Link to={`/event/${item.id}`}>view detail</Link>]}
                >
                    <List.Item.Meta
                        title={<p>{item.name}</p>}
                        description={item.description}/>
                </List.Item>
            )}
        />
    );
}
export default EventList;