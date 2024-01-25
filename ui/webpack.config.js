const path = require("path");
const webpack = require("webpack");
require("dotenv").config({ path: "./.env" });

module.exports = (env) => {
	return {
		entry: "./src/index.tsx",
		output: {
			path: path.resolve(__dirname, "dist"),
			filename: "index.bundle.js",
		},
		devServer: {
			port: 3000,
		},
		resolve: {
			modules: [__dirname, "src", "node_modules"],
			extensions: [".js", ".jsx", ".tsx", ".ts", ".*"],
		},
		plugins: [
			new webpack.DefinePlugin({
				"process.env": JSON.stringify(process.env),
			}),
		],
		module: {
			rules: [
				{
					test: /\.(js|ts)x?$/,
					exclude: /node_modules/,
					use: ["babel-loader"],
				},
				{
					test: /\.css$/,
					exclude: /node_modules/,
					use: ["style-loader", "css-loader"],
				},
				{
					test: /\.scss$/,
					exclude: /node_modules/,
					use: ["style-loader", "css-loader", "sass-loader"],
				},
				{
					test: /\.(png|svg|jpg|gif)$/,
					exclude: /node_modules/,
					use: ["file-loader"],
				},
				{
					test: /\.(png|svg|jpg|jpeg|gif|ico)$/,
					exclude: /node_modules/,
					use: ["file-loader?name=[name].[ext]"],
				},
			],
		},
	};
};
