--
-- Autogenerated by Thrift
--
-- DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
-- @generated
--


require 'Thrift'
require 'Json_constants'

Request = __TObject:new{
  msgId,
  code,
  data
}

function Request:read(iprot)
  iprot:readStructBegin()
  while true do
    local fname, ftype, fid = iprot:readFieldBegin()
    if ftype == TType.STOP then
      break
    elseif fid == 1 then
      if ftype == TType.STRING then
        self.msgId = iprot:readString()
      else
        iprot:skip(ftype)
      end
    elseif fid == 2 then
      if ftype == TType.STRING then
        self.code = iprot:readString()
      else
        iprot:skip(ftype)
      end
    elseif fid == 3 then
      if ftype == TType.STRING then
        self.data = iprot:readString()
      else
        iprot:skip(ftype)
      end
    else
      iprot:skip(ftype)
    end
    iprot:readFieldEnd()
  end
  iprot:readStructEnd()
end

function Request:write(oprot)
  oprot:writeStructBegin('Request')
  if self.msgId ~= nil then
    oprot:writeFieldBegin('msgId', TType.STRING, 1)
    oprot:writeString(self.msgId)
    oprot:writeFieldEnd()
  end
  if self.code ~= nil then
    oprot:writeFieldBegin('code', TType.STRING, 2)
    oprot:writeString(self.code)
    oprot:writeFieldEnd()
  end
  if self.data ~= nil then
    oprot:writeFieldBegin('data', TType.STRING, 3)
    oprot:writeString(self.data)
    oprot:writeFieldEnd()
  end
  oprot:writeFieldStop()
  oprot:writeStructEnd()
end

Response = __TObject:new{
  msgId,
  code,
  data
}

function Response:read(iprot)
  iprot:readStructBegin()
  while true do
    local fname, ftype, fid = iprot:readFieldBegin()
    if ftype == TType.STOP then
      break
    elseif fid == 1 then
      if ftype == TType.STRING then
        self.msgId = iprot:readString()
      else
        iprot:skip(ftype)
      end
    elseif fid == 2 then
      if ftype == TType.STRING then
        self.code = iprot:readString()
      else
        iprot:skip(ftype)
      end
    elseif fid == 3 then
      if ftype == TType.STRING then
        self.data = iprot:readString()
      else
        iprot:skip(ftype)
      end
    else
      iprot:skip(ftype)
    end
    iprot:readFieldEnd()
  end
  iprot:readStructEnd()
end

function Response:write(oprot)
  oprot:writeStructBegin('Response')
  if self.msgId ~= nil then
    oprot:writeFieldBegin('msgId', TType.STRING, 1)
    oprot:writeString(self.msgId)
    oprot:writeFieldEnd()
  end
  if self.code ~= nil then
    oprot:writeFieldBegin('code', TType.STRING, 2)
    oprot:writeString(self.code)
    oprot:writeFieldEnd()
  end
  if self.data ~= nil then
    oprot:writeFieldBegin('data', TType.STRING, 3)
    oprot:writeString(self.data)
    oprot:writeFieldEnd()
  end
  oprot:writeFieldStop()
  oprot:writeStructEnd()
end