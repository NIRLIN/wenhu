import request from '@/utils/request'

export function saveQuestion(data) {
  return request({
    url: '/creation/question/saveQuestion',
    method: 'post',
    data
  })
}

export function listAnswerByTime(data) {
  return request({
    url: '/creation/question/listAnswerByTime',
    method: 'post',
    data
  })
}

export function getQuestionById(data) {
  return request({
    url: '/creation/question/getQuestionById',
    method: 'post',
    data
  })
}
export function getAnswerListByQuestionId(data) {
  return request({
    url: '/creation/question/getQuestionAndAnswerListByQuestionId',
    method: 'post',
    data
  })
}

