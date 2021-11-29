export const isAuth = () => {
    return localStorage.getItem('token');
}

export const setAuth = (token) => {
    localStorage.setItem('token', token)
}

export const logout = () => {
    localStorage.setItem('token', null);
}
