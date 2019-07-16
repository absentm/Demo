#!/usr/bin/env python
# -*- coding: utf-8 -*-

import psutil
import click


@click.command()
@click.option("--query", is_flag=True)
@click.option("--disk", is_flag=True)
@click.option("--part", is_flag=True)
def get_disk_part():
    """Query disk part information of current system"""
    result_list = list()
    disk_list = psutil.disk_partitions()

    for item in disk_list:
        tmp_dic = dict()
        tmp_dic["device_name"] = item.device
        tmp_dic["mount_point"] = item.mountpoint
        tmp_dic["fs_type"] = item.fstype
        tmp_dic["options"] = item.opts
        result_list.append(tmp_dic)

    click.echo(result_list)


if __name__ == '__main__':
    get_disk_part()


