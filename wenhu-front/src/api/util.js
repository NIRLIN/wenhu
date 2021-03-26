import request from '@/utils/request'

export function imageUpload(data) {
  return request({
    url: '/creation/fileUpload/imageUpload',
    method: 'post',
    data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}
