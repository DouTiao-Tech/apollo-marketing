import ajax from "util/ajax.js";

const path = "/api/session";

export function currentUser() {
    return Promise.resolve("aaa");
}

export function login(principal, credential) {
    return Promise.resolve("aaa");
}

export function logout() {
    return Promise.resolve("aaa");
}