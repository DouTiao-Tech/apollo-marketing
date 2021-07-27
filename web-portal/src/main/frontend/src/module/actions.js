export const userLogin = (user) => {
    return {
        type: "LOGIN",
        payload: user
    }
};

export const userLogout = () => ({type: "LOGOUT"});