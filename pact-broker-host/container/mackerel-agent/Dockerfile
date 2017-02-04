FROM mackerel/mackerel-agent
MAINTAINER nsoushi

RUN mkdir -p /etc/mackerel-agent/conf.d/

COPY mackerel-agent/mackerel-agent.conf /etc/mackerel-agent/
COPY startup.sh /startup.sh

ENTRYPOINT ["/startup.sh"]