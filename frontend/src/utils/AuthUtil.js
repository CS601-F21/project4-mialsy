export const isAuth = (cookie) => {
    return cookie["JSESSIONID"];
}

export const setAuth = (token) => {
    localStorage.setItem('token', token)
}

export const logout = () => {
    localStorage.setItem('token', null);
}
