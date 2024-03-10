package ru.nsu.burde.crackhash.common;

import java.util.List;

public class WorkerResponseDTO {

    private String requestId;
    private int partNumber;
    private List<String> data;

    public WorkerResponseDTO(){

    }
    public WorkerResponseDTO(String requestId, int partNumber, List<String> data) {
        this.requestId = requestId;
        this.partNumber = partNumber;
        this.data = data;
    }

    public static WorkerResponseDTOBuilder builder() {
        return new WorkerResponseDTOBuilder();
    }

    public String getRequestId() {
        return this.requestId;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public List<String> getData() {
        return this.data;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WorkerResponseDTO)) return false;
        final WorkerResponseDTO other = (WorkerResponseDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$requestId = this.getRequestId();
        final Object other$requestId = other.getRequestId();
        if (this$requestId == null ? other$requestId != null : !this$requestId.equals(other$requestId)) return false;
        if (this.getPartNumber() != other.getPartNumber()) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WorkerResponseDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $requestId = this.getRequestId();
        result = result * PRIME + ($requestId == null ? 43 : $requestId.hashCode());
        result = result * PRIME + this.getPartNumber();
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "WorkerResponseDTO(requestId=" + this.getRequestId() + ", partNumber=" + this.getPartNumber() + ", data=" + this.getData() + ")";
    }

    public static class WorkerResponseDTOBuilder {
        private String requestId;
        private int partNumber;
        private List<String> data;

        WorkerResponseDTOBuilder() {
        }

        public WorkerResponseDTOBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public WorkerResponseDTOBuilder partNumber(int partNumber) {
            this.partNumber = partNumber;
            return this;
        }

        public WorkerResponseDTOBuilder data(List<String> data) {
            this.data = data;
            return this;
        }

        public WorkerResponseDTO build() {
            return new WorkerResponseDTO(this.requestId, this.partNumber, this.data);
        }

        public String toString() {
            return "WorkerResponseDTO.WorkerResponseDTOBuilder(requestId=" + this.requestId + ", partNumber=" + this.partNumber + ", data=" + this.data + ")";
        }
    }
}