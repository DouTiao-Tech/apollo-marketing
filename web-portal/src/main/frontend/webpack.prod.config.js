const path = require('path');
const _ = require('lodash');
const {IgnorePlugin} = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const {BundleAnalyzerPlugin} = require('webpack-bundle-analyzer');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const DynamicCdnWebpackPlugin = require('dynamic-cdn-webpack-plugin');

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
                    }
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
            api: path.resolve(__dirname, "src/api"),
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
            loader: MiniCssExtractPlugin.loader
        },
        {
            loader: 'css-loader', // translates CSS into CommonJS
            options: {
                modules: true
            }
        },
        {
            loader: 'less-loader', // compiles Less to CSS
            options: {
                javascriptEnabled: true
            }
        }
    ],
    exclude: [path.resolve(__dirname, 'node_modules')]
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

function buildPlugins(args = {}) {
    const {analysis} = args;
    return _.compact([
        new CleanWebpackPlugin(['dist']),
        new IgnorePlugin({
            resourceRegExp: /^\.\/locale$/,
            contextRegExp: /moment$/
        }),
        new HtmlWebpackPlugin({
            title: "开发中...",
            template: "./static/index.html"
        }),
        new MiniCssExtractPlugin({
            filename: '[name].[hash].css',
            chunkFilename: '[id].[hash].css',
        }),
        new DynamicCdnWebpackPlugin(),
        analysis ? new BundleAnalyzerPlugin() : undefined
    ]);
}

module.exports = function (env, args) {
    return {
        mode: 'production',
        entry: {
            app: './src/index.js',
            vendors: []
        },
        externals: isDevMode() ? undefined : {
            lodash: 'window._'
        },
        output: {
            // 表示在引入静态资源时，从根路径开始引入
            publicPath: '/',
            filename: '[name].[chunkhash].js',
            path: path.resolve(__dirname, 'dist')
        },
        module: {
            rules: [
                babelLoaderRule,
                fileLoaderRule,
                lessLoaderRule
            ]
        },
        // optimization: buildOptimization(),
        resolve: buildResolve(),
        plugins: buildPlugins(args)
    }
};