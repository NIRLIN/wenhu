import request from '@/utils/request'

export function saveQuestion(data) {
  return request({
    url: '/creation/answer/saveQuestion',
    method: 'post',
    data
  })
}

