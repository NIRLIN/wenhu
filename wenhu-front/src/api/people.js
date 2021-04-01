import request from '@/utils/request'

export function getUserInfo(data) {
  return request({
    url: '/people/user/getUserInfo',
    method: 'post',
    data
  })
}

export function getHomepageByUserId(data) {
  return request({
    url: '/people/user/getHomepageByUserId',
    method: 'post',
    data
  })
}

export function saveChangeHomepage(data) {
  return request({
    url: '/people/user/saveChangeHomepage',
    method: 'post',
    data
  })
}

export function listAnswerByUserId(data) {
  return request({
    url: '/people/user/listAnswerByUserId',
    method: 'post',
    data
  })
}

export function listArticleByUserId(data) {
  return request({
    url: '/people/user/listArticleByUserId',
    method: 'post',
    data
  })
}
export function listQuestionByUserId(data) {
  return request({
    url: '/people/user/listQuestionByUserId',
    method: 'post',
    data
  })
}

export function listCollectByUserId(data) {
  return request({
    url: '/people/user/listCollectByUserId',
    method: 'post',
    data
  })
}

export function listFollowByUserId(data) {
  return request({
    url: '/people/user/listFollowByUserId',
    method: 'post',
    data
  })
}

export function checkOldPhoneNumber(data) {
  return request({
    url: '/people/user/checkOldPhoneNumber',
    method: 'post',
    data
  })
}

export function checkNewPhoneNumber(data) {
  return request({
    url: '/people/user/checkNewPhoneNumber',
    method: 'post',
    data
  })
}

export function changeNewPhoneNumber(data) {
  return request({
    url: '/people/user/changeNewPhoneNumber',
    method: 'post',
    data
  })
}

export function getResumeByUserId(data) {
  return request({
    url: '/people/user/getResumeByUserId',
    method: 'post',
    data
  })
}

export function saveResumeByUserId(data) {
  return request({
    url: '/people/user/saveResumeByUserId',
    method: 'post',
    data
  })
}

export function getLoginLogByUserId(data) {
  return request({
    url: '/people/user/getLoginLogByUserId',
    method: 'post',
    data
  })
}

export function getHeadImageByUserId(data) {
  return request({
    url: '/people/user/getHeadImageByUserId',
    method: 'post',
    data
  })
}

export function saveHeadImageByUserId(data) {
  return request({
    url: '/people/user/saveHeadImageByUserId',
    method: 'post',
    data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
