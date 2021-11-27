const AuthProviderUtil = {
    isAuthenticated: false,
    signin(callback: VoidFunction) {
        AuthProviderUtil.isAuthenticated = true;
        setTimeout(callback, 100); // fake async
    },
    signout(callback: VoidFunction) {
        AuthProviderUtil.isAuthenticated = false;
        setTimeout(callback, 100);
    }
  };
  
  export { AuthProviderUtil };