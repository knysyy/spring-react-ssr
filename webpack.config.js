const path = require('path')
const glob = require('glob')

module.exports = {
  mode: 'production',
  entry: () => {
    const entries = {};
    glob.sync('./client/pages/**/*.jsx').forEach(file => {
      const name = file.replace('./client', '').replace('.jsx', '');
      entries[name] = path.resolve(file);
    });
    return entries;
  },
  output: {
    path: path.resolve('src/main/resources/static/js'),
    filename: "[name].js",
    globalObject: "this"
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        use: [
          {
            loader: 'babel-loader',
            options: {
              presets: [
                [
                  '@babel/preset-react',
                  {
                    runtime: 'automatic'
                  }
                ]
              ]
            }
          }
        ]
      }
    ]
  },
  resolve: {
    extensions: [".js", ".jsx"]
  },
  target: ["web", "es6"]
};
