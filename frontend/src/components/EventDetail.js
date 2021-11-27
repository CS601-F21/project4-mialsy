import * as React from 'react';
import { useParams } from 'react-router';

const EventDetail = () => {
    const params = useParams();

    // const getEventDetail = (id) => {
        

    // };

    return (<div>{params.id} </div>);
}
export default EventDetail;