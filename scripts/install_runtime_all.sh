#!/bin/bash

microdnf update \
    && microdnf install gcc gcc-c++ golang java python3 ruby \
    && microdnf clean all
