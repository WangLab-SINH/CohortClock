import request from '@/utils/request'

export async function login (data) {
  return request({
    url: '/login',
    method: 'post',
    data,
  })
}

export async function PhotoInfoData (page, limit) {
  return request({
    url: '/photoList',
    method: 'get',
    params: {
      page,
      limit
    }
  })
}
export async function Getdaytimedatalist () {
  return request({
    url: '/getdaytimedata',
    method: 'get',
  })
}


export async function Gethourtimedatalist () {
  return request({
    url: '/gethourtimedata',
    method: 'get',
  })
}


export async function Getaddbpdyindexlist () {
  return request({
    url: '/getaddbodyindex',
    method: 'get',
  })
}



export async function Getdiettypedatalist () {
  return request({
    url: '/getdiettypedata',
    method: 'get',
  })
}

export async function GetUserinfolist (page, limit) {
  return request({
    url: '/userinfo',
    method: 'get',
    params: {
      page,
      limit
    }
  })
}


export async function PressureInfoData (page, limit) {
  return request({
    url: '/bloodpressure',
    method: 'get',
    params: {
      page,
      limit
    }
  })
}



export async function EditPhoto (data) {
  return request({
    url: '/editphoto',
    method: 'post',
    data: data,
  })
}

export async function EditEatFlag (data) {
  return request({
    url: '/editeatflag',
    method: 'post',
    data: data,
  })
}


export async function EditBodyIndexType (data) {
  return request({
    url: '/editbodyindextype',
    method: 'post',
    data: data,
  })
}


export async function BodyIndexData (select_body_index,page, limit) {
  return request({
    url: '/bodyindex',
    method: 'get',
    params: {
      select_body_index,
      page,
      limit
    }
  })
}
