#!/bin/bash
set -e

echo "🛠️ Step 1: Preparing SSH keys..."
# Use /tmp/ instead of ~/.ssh/ to avoid permission issues
KEY_PATH="/tmp/ansible_id_rsa"
rm -f "$KEY_PATH"
cp ansible/id_rsa "$KEY_PATH"
chmod 400 "$KEY_PATH"

echo "📝 Step 2: Running Ansible Playbook..."
# Run the playbook using the key in /tmp/
ansible-playbook -i ansible/inventory.ini ansible/deploy.yml
