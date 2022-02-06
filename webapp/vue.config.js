module.exports = {
  chainWebpack: config => {
  },
  css: {
    loaderOptions: {
      sass: {
        sassOptions: {
          includePaths: ['./node_modules'],
          quietDeps: true
        },
        prependData: '@import "~@/styles/vars.scss";'
      },
    }
  }
}