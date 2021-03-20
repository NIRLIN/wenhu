import request from '@/utils/request'

export function getHotList() {
  return request({
    url: '/hot/listHotQuestion',
    method: 'get'
  })
}

