module.exports.parse = async (raw, {axios, yaml, notify, console}, {name, url, interval, selected}) => {
    const obj = yaml.parse(raw)
    const rules = [
        'GEOIP,CN,DIRECT',
        'RULE-SET,ChinaMax,DIRECT',
        'MATCH,SSRDOG'
    ]
    obj.rules = rules
    return yaml.stringify(obj)
}