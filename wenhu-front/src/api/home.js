import request from '@/utils/request'

export function getHotList() {
  return request({
    url: '/home/hot/listHotQuestion',
    method: 'get'
  })
}

