import axios from 'axios'
export const HOST = '/api'

export const ERR_OK = 200;

export function Login(data) {
  const url = '/api/users/login'
  return axios.post(url, data)
}

export function Register() {
  const url = HOST + '/api/v1/auth/register'
  return axios.post(url)
}
