# coding=utf-8

import requests

req_session = requests.Session()


def run_requests(method, url, headers, rest_data=None):
    """
    Run http or https request
    :param method: request method
    :param url: request url
    :param headers: request header
    :param rest_data: request body
    :return:
    """
    if method == 'GET':
        return req_session.get(url, headers=headers, verify=False)
    elif method == 'PUT':
        if rest_data is not None:
            return req_session.put(url, json=rest_data, headers=headers, verify=False)
        else:
            return req_session.put(url, headers=headers, verify=False)
    elif method == 'POST':
        return req_session.post(url, json=rest_data, headers=headers, verify=False)
    elif method == 'DELETE':
        return req_session.delete(url, headers=headers, verify=False)
