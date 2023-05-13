/**
 * @param {string} url
 * @param {string} method
 * @param {object} if method is get or delete, paramater is query, else paramater is body
 * @return {Promise}
 */
function request(url, method, paramater) {
  let body = null
  if (method === 'get' || method == 'delete') {
    // foreach object to query string
    let query = '?'
    for (let key in paramater) {
      query += `${key}=${paramater[key]}&`
    }
    url += query
  } else {
    body = JSON.stringify(paramater)
  }
  return fetch(url, {
    method,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization:
        'Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxMDAsInVzZXJfa2V5IjoiMGE4MDIwNTMtNDU1MC00ODMyLTg3Y2UtYzIzZDFiOWIxMzhlIiwidXNlcm5hbWUiOiJ6cyJ9.xIDtQva_HuKy4-lQOgbVQiemsLO0xhBwXjrZDw97VrECGrkVkZHSr893rEpbC7p5c4vIJarX_ItT9m6BJn6ytA',
    },
    body,
  })
    .then((res) => res.json())
    .then((json) => json.data)
}

export function get(url, parameter) {
  return request(url, 'get', parameter)
}
export function post(url, body) {
  return request(url, 'post', body)
}
export function del(url, parameter) {
  return request(url, 'delete', parameter)
}
export function put(url, body) {
  return request(url, 'put', body)
}
