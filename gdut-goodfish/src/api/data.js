import axios from 'axios'
export const HOST = '/api'

export const ERR_OK = 200;

export function getSearchResult({ itemName = '', page = 0, pageSize = 10, categoryId = '', location = '', maxPrice = '', minPrice = '' }) {
  const url = HOST + '/item/pageQuery';

  // 过滤掉空字符串的参数
  const params = {
    itemName,
    page,
    pageSize,
    categoryId,
    location: location || undefined,
    maxPrice: maxPrice || undefined,
    minPrice: minPrice || undefined
  };

  return axios.get(url, { params });
}
export function uploadItem(file) {
  const url = HOST + '/upload';
  const formData = new FormData();
  formData.append('file', file);

  return axios.post(url, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
}
export function getItems() {
  const url = HOST + '/item'
  return axios.get(url)
}
export function getCategory() {
  const url = HOST + '/api/v1/items/search'
  return axios.get(url)
}

export function getIndexbanner() {
  const url = HOST + '/indexbanner'
  return axios.get(url)
}

export function getIconlist() {
  const url = HOST + '/iconlist'
  return axios.get(url)
}

export function getTypelist() {
  const url = HOST + '/typelist'
  return axios.get(url)
}

export function getSmBanner() {
  const url = HOST + '/smallbanner'
  return axios.get(url)
}

export function getIndexNav() {
  const url = HOST + '/indexnav'
  return axios.get(url)
}

export function getBanner() {
  const url = HOST + '/banner'
  return axios.get(url)
}

export function getInterests() {
  const url = HOST + '/interests'
  return axios.get(url)
}
export function getQuestions() {
  const url = HOST + '/questions'
  return axios.get(url)
}

export function getTuhao() {
  const url = HOST + '/tuhao'
  return axios.get(url)
}

export function getChina() {
  const url = HOST + '/china'
  return axios.get(url)
}

export function getLists() {
  const url = HOST + '/lists'
  return axios.get(url)
}

export function newItem() {
  const url = HOST + '/api/v1/items'
  return axios.post(url)
}
