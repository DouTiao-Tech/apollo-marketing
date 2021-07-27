const path = require('path');
const {IgnorePlugin} = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

/**
 * https://webpack.js.org/configuration/dev-server
 */
function buildDevServer() {
    return {
        contentBase: './dist',
        port: 3000,
        compress: true,
        historyApiFallback: {
            rewrites: [
                {from: /^\/*$/, to: '/'}
            ]
        },
        proxy: {
            '/api': 'http://localhost:2000'
        }
    };
}

/**
 * https://babeljs.io/docs/en/presets
 * https://babeljs.io/docs/en/plugins
 * @returns {{presets: *[], plugins: *[]}}
 */
function babelOptions() {
    return {
        "presets": [
            "@babel/react",
            [
                "@babel/env",
                {
                    "targets": {
                        "chrome": "58",
                        "ie": "11"
                    },
                    "debug": true
                }
            ]
        ],
        "plugins": [
            ["@babel/plugin-proposal-decorators", {legacy: true}],
            ["@babel/plugin-proposal-class-properties"],
            ["@babel/plugin-transform-runtime"],
            ["@babel/plugin-proposal-export-default-from"],
            ["import", {
                "libraryName": "antd",
                "style": true,   // or 'css'
            }]
        ]
    };
}

/**
 * https://webpack.js.org/configuration/resolve
 */
function buildResolve() {
    return {
        extensions: ['.js', '.jsx', '.json'],
        alias: {
            util: path.resolve(__dirname, "src/util"),
            service: path.resolve(__dirname, "src/service"),
            layout: path.resolve(__dirname, "src/layout"),
            common: path.resolve(__dirname, "src/common"),
            module: path.resolve(__dirname, "src/module"),
            component: path.resolve(__dirname, "src/component"),
            ui: path.resolve(__dirname, "src/ui"),
            config$: path.resolve(__dirname, "src/config.js")
        }
    };
}

/**
 * https://webpack.js.org/loaders/babel-loader
 */
const babelLoaderRule = {
    test: /\.js[x]?$/,
    include: path.resolve(__dirname, 'src'),
    use: {
        loader: 'babel-loader',
        options: babelOptions()
    }
};

/**
 * https://webpack.js.org/loaders/less-loader
 */
const lessLoaderRule = {
    test: /\.(less|css)$/,
    use: [
        {
            /* 使用MiniCss(<link>外部文件)替换style-loader(<style>js动态插入) */
            loader: MiniCssExtractPlugin.loader,
            options: {
                /* Hot Module Reloading */
                hmr: true
            }
        },
        {
            loader: 'css-loader', // translates CSS into CommonJS
            options: {
                modules: true,
                localIdentName: '[name]_[local]_[hash:base64:5]'
            }
        },
        {
            loader: 'less-loader', // compiles Less to CSS
        }
    ],
    exclude: [path.resolve(__dirname, 'node_modules')]
};

const vendorsCssLoaderRule = {
    test: /\.(less|css)$/,
    use: [
        {
            /* 使用MiniCss(<link>外部文件)替换style-loader(<style>js动态插入) */
            loader: MiniCssExtractPlugin.loader,
        },
        {
            loader: 'css-loader', // translates CSS into CommonJS
        },
        {
            loader: 'less-loader', // compiles Less to CSS
            options: {
                javascriptEnabled: true,
                modifyVars: {
                    'hack': `true; @import "${path.resolve(__dirname, 'src/common/theme.less')}";`
                }
            }
        }
    ],
    include: [path.resolve(__dirname, 'node_modules')]
};


const fileLoaderRule = {
    test: /\.(png|jpe?g|gif|svg|eot|ttf|woff|woff2)$/,
    use: [{
        loader: 'url-loader',
        options: {
            limit: 8192,
            publicPath: '/img/',
            outputPath: path.resolve(__dirname, 'dist', 'img')
        }
    }]
};

function buildPlugins() {
    return [
        new IgnorePlugin({
            resourceRegExp: /^\.\/locale$/,
            contextRegExp: /moment$/
        }),
        new HtmlWebpackPlugin({
            title: "开发中...",
            template: "./static/index.html"
        }),
        new MiniCssExtractPlugin({
            filename: '[name].css',
            chunkFilename: '[id].css',
        }),
    ];
}

module.exports = {
    mode: 'development',
    entry: {
        app: './src/index.js',
        vendors: ['antd']
    },
    output: {
        // 表示在引入静态资源时，从根路径开始引入
        publicPath: '/',
        filename: '[name].[chunkhash].js',
        path: path.resolve(__dirname, 'dist')
    },
    devServer: buildDevServer(),
    module: {
        rules: [
            babelLoaderRule,
            fileLoaderRule,
            lessLoaderRule,
            vendorsCssLoaderRule
        ]
    },
    resolve: buildResolve(),
    plugins: buildPlugins()
};