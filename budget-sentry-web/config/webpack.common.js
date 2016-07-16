var webpack = require('webpack');
var helpers = require('./helpers');

module.exports = {
    context: helpers.root('src'),
    resolve: {
        extensions: ['', '.js', '.ts'],
        root: [
            helpers.root('src')
        ]
    },
    module: {
        loaders: [
            {
                test: /\.ts$/,
                loaders: ['ts']
            },
            {
                test: /\.html$/,
                loader: 'html'
            },
            {
                test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/,
                loader: 'file?name=assets/[name].[hash].[ext]'
            },
            {
                test: /\.scss$/,
                loader: 'raw!sass'
            }
        ]
    },
    ts: {
        configFileName: helpers.root('.') + '/tsconfig.json'
    }
};