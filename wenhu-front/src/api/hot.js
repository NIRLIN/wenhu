import request from '@/utils/request'

export function getHotList() {
  return request({
    url: '/hot/hot/listHotQuestion',
    method: 'get'
  })
}

