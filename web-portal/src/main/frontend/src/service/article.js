import ajax from "util/ajax.js";

const path = "/api/article";

export function findArticleById(id) {
    return Promise.resolve("aaa");
}

export function search(query, page = 1, size = 15) {
    return Promise.resolve("aaa");
}

export function saveArticle(article) {
    return Promise.resolve("aaa");
}

export function updateArticle(id, article) {
    return Promise.resolve("aaa");
}

export function deleteArticle(id) {
    return Promise.resolve("aaa");
}

export function likeTitlePrefix(titlePrefix, size) {
    return Promise.resolve("aaa");
}

export function likeFuzzyTitle(fuzzyTitle, size) {
    return Promise.resolve("aaa");
}