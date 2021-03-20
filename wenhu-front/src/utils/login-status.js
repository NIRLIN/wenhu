import Cookies from 'js-cookie'

const CookieKey = 'wenhu_user_id'

export function getCookie() {
  return Cookies.get(CookieKey)
}

export function setCookie(value) {
  return Cookies.set(CookieKey, value)
}

export function removeCookie() {
  return Cookies.remove(CookieKey)
}

