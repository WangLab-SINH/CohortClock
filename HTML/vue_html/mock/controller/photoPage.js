module.exports = [
  {
    url: '/taxons/photopage',
    type: 'get',
    response() {
      return { code: 200, msg: 'success', data }
    },
  },
]
