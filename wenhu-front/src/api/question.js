import request from '@/utils/request'

export function saveQuestion(data) {
  return request({
    url: '/creation/question/saveQuestion',
    method: 'post',
    data
  })
}

export function getListAnswerByTime(data) {
  return request({
    url: '/creation/question/saveQuestion',
    method: 'post',
    data
  })
}

export function getAnswerListByQuestionId(data) {
  return request({
    url: '/creation/question/getUserQuestionListById',
    method: 'post',
    data
  })
}

