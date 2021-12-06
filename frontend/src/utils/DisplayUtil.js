import moment, { unix } from "moment";

export const getFormattedTime = (unixTime) => {
    const day = moment.unix(unixTime).format('ddd');
    const dateTime = moment.unix(unixTime).format("MMM, DD, YYYY HH:mm");
    return `${day} ${dateTime}`;
}

export const isPassedTime = (unixTime) => {
    const current = moment().unix(); 
    console.log(current)
    console.log(unixTime)
    console.log(moment(unixTime).isBefore(current));
    return moment(unixTime).isBefore(current);
}