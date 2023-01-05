import requests
import lxml
from bs4 import BeautifulSoup as bs
import subprocess


class Requester:

    _base_uri = "https://www.acmicpc.net/problem/"
    _base_headers = {
        'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Whale/3.18.154.8 Safari/537.36'
    }
    def __init__(self):
        pass

    def request(self, problem_number):
        uri = Requester._base_uri + problem_number
        return requests.get(uri, headers = Requester._base_headers)

class Parser:

    def __init__(self):
        pass

    def parse(self, response):
        soup = bs(response.text, 'lxml')
        return self._find_samples(soup)


    def _find_samples(self, soup):
        data = soup.find_all('pre', {'class': 'sampledata'})
        if len(data) % 2 != 0:
            raise "invalid length. not even size"
        return [Sample(input.text, output.text) for input, output in zip(data[::2], data[1::2])]

class Sample:

    def __init__(self, input, output):
        self._input = input.strip()
        self._output = output.strip()

    def __repr__(self):
        return f"input: {self._input}, output: {self._output}"

    @property
    def input(self):
        return self._input

    @property
    def output(self):
        return self._output

class Runner:

    def __init__(self):
        pass

    def run(self, file_path, sample_data):
        temp = []
        for sample in sample_data:
            actual = self._execute(file_path, sample.input)
            if sample.output in actual:
                temp.append(True)
            else:
                temp.append(False)
        return temp

    def _execute(self, file_path, sample_input):
        completed = subprocess.run(["python", file_path], input=sample_input, text=True, capture_output=True)
        return completed.stdout.strip()

requester = Requester()
response = requester.request("14890")

parser = Parser()
samples = parser.parse(response)

runner = Runner()
result = runner.run("bj14890.py", samples)

