---
title: React Env From Scratch
description: set up react environment from scratch
date: 2022-10-1 19:38:09
tags:
- howto
categories:
- react
---

# Start React From Setting up Environment 
Start from script to [webpack](https://webpack.js.org/)


## What is React
A JavaScript library for building user interfaces

## How to Use it ?
### 1. Use React In Html
```html
<!DOCTYPE html>

<html>
    <head>
        <script src="https://unpkg.com/react@18/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js" crossorigin></script>
    </head>
    <body>
        <div id="root"></div>
    </body>
    <script>
        'use strict';

        const e = React.createElement;
        // old style with class
        class LikeButton extends React.Component {
            constructor(props) {
                super(props);
                this.state = { liked: false };
            }

            render() {
                if (this.state.liked) {
                    return 'You liked this.';
                }

                return e(
                    'button',
                    { onClick: () => this.setState({ liked: true }) },
                    'Like'
                    );
                }
        }

        // use react hooks with function
        const useState = React.useState;

        function LikeButton() {
            const [like, setLike] = useState(false);
            if (like) {
                return 'You liked this';
            }
            return e(
                'button',
                { onClick: () => setLike(true) },
                'Like'
            )
        }

        // we can create nested children
        function CounterButton() {
            const [count, setCount] = useState(0);
            return e(
                'div',
                { onClick: () => setCount(count + 1) },
                e(
                    'div',
                    {
                        id: 'v',
                        className: 'v',
                        style: {color: 'red', fontSize: '20px'},
                    },
                    'Counter:',
                    e('ul', {},
                        [
                            e('li', { style: {color: 'brown' }}, count),
                            e('li', { style: {color: 'green' }}, count),
                            e('li', { style: {color: 'blue' }}, count)
                        ]
                    ),
                    e(
                        'button',
                        {},
                        count
                    ),
                    
                ),                
            )
        }
        const domContainer = document.querySelector('#root');
        const root = ReactDOM.createRoot(domContainer);
        root.render(e(CounterButton));
    </script>
</html>
```

We can use react in `html` without anything else, however this is not our 
best choice.

### 2. Use React In Webpack
I think webpack explains itself with its name, it packs everything you need.
#### 2.1 Init webpack
We need `npm` to install `webpack`
```sh
cd ~ & mkdir webpack-demo 
cd webpack-demo
npm init -y
npm install webpack webpack-cli --save-dev
```
Then you can check the directory tree with `tree` command
```sh
❯ tree . -L 1
├── node_modules
├── package-lock.json
└── package.json
```
#### 2.2 Create src directory
Then we need add our own `src` directory to make the directory tree like this
```sh
├── index.html
├── node_modules
├── package-lock.json
├── package.json
└── src
    └── index.js
```
Then We create index.html
```html
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8" />
        <title>react and webpack</title>
    </head>
    <body>
        <div id="root"></div>
        <script src="./src/index.js"></script>
    </body>
</html>
```
Then we create index.js
```js
'use strict';
function App() {
    const element = document.createElement('div');
    element.innerHTML = 'Hello React!';
    return (        
       element
    );
}
const domContainer = document.querySelector('#root');
domContainer.appendChild(App());
```

#### 2.3 Complete webpack config

Then we need adjust package.json
```json
 {
   "name": "webpack-demo",
   "version": "1.0.0",
   "description": "",
   // "main": "index.js",
   "private": true,
   "scripts": {
     "test": "echo \"Error: no test specified\" && exit 1"
   },
   "keywords": [],
   "author": "",
   "license": "MIT",
   "devDependencies": {
     "webpack": "^5.38.1",
     "webpack-cli": "^4.7.2"
   }
 }
```
Then we need create a `output` directory, where `webpcak` bundle everything,
and we also need move `index.html` to `dist` directory.
```html
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8" />
        <title>react and webpack</title>
    </head>
    <body>
        <div id="root"></div>
        <script src="main.js"></script>
    </body>
</html>
```

```sh
├── dist
│   └── index.html
├── node_modules
├── package-lock.json
├── package.json
└── src
    └── index.js
```
Then we add `webpcak.config.js`
```js
const path = require('path');

module.exports = {
    entry: './src/index.js',
    output: {
        filename: 'main.js',
        path: path.resolve(__dirname, 'dist'),
    },
};
```
```sh
├── dist
│   └── index.html
├── node_modules
├── package-lock.json
├── package.json
├── src
│   └── index.js
└── webpcak.config.js
```

#### 2.4 We bundle
```sh
npx webpack --config webpack.config.js
```
We now can bundle `src/index` to `dist/main`.
```sh
dist
├── index.html
└── main.js
```

We can set up `package.json` with shortcut.
```json
"scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "build": "webpack"
}
```
we can use one command to finish the bundle
```sh
npm run build
```

#### 2.5 Make it Dynamic
Fow now, we do all the works to produce the static file.
We want it to be alive, so we need `server`
```sh
npm install webpack-dev-server --save-dev
```
Then We add `script` in `package.json`
```json
"scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "build": "webpack --config webpack.config.js",
    "serve": "webpack serve"
}
```


### 3. The Final Config

The final config needs more `webpack` knowledge, which need use
the `webpack` official to complete.

package.json
```json
{
  "name": "webpack-demo",
  "version": "1.0.0",
  "description": "",
  "private": true,
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "build": "webpack --config webpack.config.js",
    "serve": "webpack serve --mode development"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "@babel/core": "^7.19.3",
    "@babel/preset-env": "^7.19.4",
    "@babel/preset-react": "^7.18.6",
    "babel-loader": "^8.2.5",
    "html-webpack-plugin": "^5.5.0",
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "webpack": "^5.4.0",
    "webpack-cli": "^4.2.0",
    "webpack-dev-server": "^4.11.1"
  },
  "dependencies": {}
}
```
webpack.config.js
```js
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    name: 'config',
    mode: 'development',
    entry: {
        index: './src/index.js',
    },
    devServer: {
        static: './dist',
        hot: true,
    },
    devtool: 'inline-source-map',
    plugins: [
        new HtmlWebpackPlugin({
            title: 'dev',
        })
    ],
    module: {
        rules: [
            {
                test: /\.jsx?$/, // 用正则来匹配文件路径，这段意思是匹配 js 或者 jsx
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            '@babel/preset-env', 
                            ["@babel/preset-react", {"runtime": "automatic"}]
                        ]
                    }
                }
            }
        ]
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
        clean: true,
        publicPath: '/'
    },
    optimization: {
        runtimeChunk: 'single',
    },
};
```
index.js
```js

import { useState } from 'react';
import { createRoot } from 'react-dom/client';

function App() {
    const element = document.createElement('div');
    element.setAttribute("id", "root");
    return element;
}
document.body.appendChild(App());

function Counter() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <h1>{count}</h1>
      <button onClick={() => setCount(count + 1)}>
        Increment
      </button>
    </div>
  );
}
const root = createRoot(document.getElementById('root'));
root.render(<Counter />);
```

## Ref
[webpack official](https://webpack.js.org/guides/getting-started/)