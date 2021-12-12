import moment from "moment";

export const getFormattedTime = (unixTime) => {
  const day = moment.unix(unixTime).format("ddd");
  const dateTime = moment.unix(unixTime).format("MMM, DD, YYYY HH:mm");
  return `${day} ${dateTime}`;
};

export const isPassedTime = (unixTime) => {
  const current = moment().unix();
  return moment(unixTime).isBefore(current);
};
