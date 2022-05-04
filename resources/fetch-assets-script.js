const  https = require('https');
const { existsSync, mkdirSync, createWriteStream } = require('fs');

const MAX_DOWNLOAD_QUEUE = 10;
const ASSETS_SOURCES = './Assets'

function makeDir() {
	if (!existsSync(ASSETS_SOURCES)){
		mkdirSync(ASSETS_SOURCES);
	}
}

var queue = 0;
var running = true;

function download(url, fileName) {
	https.get(url, response => {
		if(response.statusCode < 400) {
			const file = createWriteStream(fileName);
			response.pipe(file);
			file.on('finish', () => {
				file.close();
				queue--;
			});
		} else {
			running = false;
		}
	})
};

/*
Fetch all available assets
*/
async function fetchAssets() {
	makeDir();
	let i = 1;
	while(running) {
		queue++;
		download(`https://www.gamepikachu.vn/games/games/pikachu-do-dien/images/pieces${i}.png`, `${ASSETS_SOURCES}/icon${i}.png`);
		await new Promise(resolve => {
			const wait = setInterval(() => {
				if(queue < MAX_DOWNLOAD_QUEUE) {
					clearInterval(wait);
					resolve();
				}
			}, 50)
		});
		i++;
	}
}

fetchAssets();


