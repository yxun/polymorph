#!/bin/bash

sudo systemctl start sshd
sudo systemctl enable  sshd
# sudo semanage port -a -t ssh_port_t -p tcp [port]

sudo dnf install -y podman
sudo setsebool -P container_manage_cgroup on

systemctl --user enable --now podman.socket
sudo loginctl enable-linger $USER

podman --remote info