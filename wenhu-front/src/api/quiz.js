import request from '@/utils/request'

export function saveQuestion(data) {
  return request({
    url: '/creation/question/saveQuestion',
    method: 'post',
    data
  })
}

